package com.playtomic.tests.wallet.service.impl;

import com.playtomic.tests.wallet.error.WalletException;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.model.WalletOperation;
import com.playtomic.tests.wallet.repository.WalletRepository;
import com.playtomic.tests.wallet.service.PaymentService;
import com.playtomic.tests.wallet.error.PaymentServiceException;
import com.playtomic.tests.wallet.service.WalletService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;
    @Mock
    private PaymentService paymentService;

    WalletService walletService = new WalletServiceImpl();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(walletService, "walletRepository", walletRepository);
        ReflectionTestUtils.setField(walletService, "paymentService", paymentService);
    }

    @Test
    public void getWalletByIdTest(){
        Mockito.when(walletRepository.getById(Matchers.anyString())).thenReturn(new Wallet());

        Wallet wallet = walletService.getWalletById("1");

        Assert.assertNotNull(wallet);
        Mockito.verify(walletRepository).getById(Matchers.anyString());
    }

    @Test
    public void modifyWalletTest(){
        Mockito.when(walletRepository.getById(Matchers.anyString())).thenReturn(new Wallet(BigDecimal.valueOf(100D)));
        Mockito.when(walletRepository.save(Matchers.any(Wallet.class))).thenReturn(new Wallet(BigDecimal.valueOf(110D)));

        Wallet wallet = walletService.modifyWallet(new WalletOperation(BigDecimal.valueOf(10D)));

        Assert.assertNotNull(wallet);
        Mockito.verify(walletRepository).getById(Matchers.anyString());
        Mockito.verify(walletRepository).save(Matchers.any(Wallet.class));
        Assert.assertEquals(wallet.getAmount(), BigDecimal.valueOf(110D));
    }

    @Test
    public void thirdPartyChargeWalletTest() throws WalletException, PaymentServiceException {

        Mockito.when(walletRepository.getById(Matchers.anyString())).thenReturn(new Wallet(BigDecimal.valueOf(100D)));
        Mockito.when(walletRepository.save(Matchers.any(Wallet.class))).thenReturn(new Wallet(BigDecimal.valueOf(110D)));

        Wallet wallet = walletService.thirdPartyChargeWallet(new WalletOperation(BigDecimal.valueOf(10D)));

        Assert.assertNotNull(wallet);
        Mockito.verify(walletRepository).getById(Matchers.anyString());
        Mockito.verify(paymentService).charge(Matchers.any(BigDecimal.class));
        Mockito.verify(walletRepository).save(Matchers.any(Wallet.class));
        Assert.assertEquals(wallet.getAmount(), BigDecimal.valueOf(110D));
    }

    @Test(expected = WalletException.class)
    public void thirdPartyChargeWalletErrorTest() throws WalletException, PaymentServiceException {

        Mockito.when(walletRepository.getById(Matchers.anyString())).thenReturn(new Wallet(BigDecimal.valueOf(100D)));
        Mockito.doThrow(new PaymentServiceException()).when(paymentService).charge(Matchers.any(BigDecimal.class));

        Wallet wallet = walletService.thirdPartyChargeWallet(new WalletOperation(BigDecimal.valueOf(10D)));

        Assert.assertNotNull(wallet);
        Mockito.verify(walletRepository).getById(Matchers.anyString());
        Mockito.verify(paymentService).charge(Matchers.any(BigDecimal.class));
    }
}
