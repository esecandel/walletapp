package com.playtomic.tests.wallet.api;

import com.playtomic.tests.wallet.error.WalletException;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.model.WalletOperation;
import com.playtomic.tests.wallet.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class WalletController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WalletController.class);

    @Autowired
    private WalletService walletService;

    /**
     * Consultar un bono por su identificador.
     * @param walletId identificador del monedero
     * @return entidad monedero
     */
    @RequestMapping(value = "/{walletId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Wallet getWallet(@PathVariable String walletId) {

        LOGGER.info("GET wallet by id {}", walletId);

        return walletService.getWalletById(walletId);
    }

    /**
     * Descontar saldo del monedero (un cobro) o
     * devolver saldo al monedero (una devolución).
     * @param walletOperation operacion sobre el monedero
     * @return monedero modificado
     */
    @RequestMapping(value = "/{walletId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Wallet updateWallet(@RequestBody WalletOperation walletOperation) {

        LOGGER.info("PUT wallet by id {}: {}", walletOperation.getWalletId(), walletOperation);

        return walletService.modifyWallet(walletOperation);
    }

    /**
     * Descontar saldo del monedero (un cobro) o
     * devolver saldo al monedero (una devolución).
     * @param walletOperation operacion sobre el monedero
     * @return monedero modificado
     */
    @RequestMapping(value = "/charge/{walletId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Wallet chargeWallet( @RequestBody WalletOperation walletOperation) throws WalletException {

        LOGGER.info("PUT wallet by id {}: {}", walletOperation.getWalletId(), walletOperation);

        return walletService.thirdPartyChargeWallet(walletOperation);
    }


}
