### Relações base de dados 

* cliente(num_cliente{pk}, nome, morada, email, telemovel, nif, password, num_conta{unique, NN}, saldo, saldo_cativo)
* cartao(num_cartao{pk}, data_validade, estado, conta{fk, NN})
* credito(num_cartao{fk, pk}, data_vencimento, saldo_limite)
* pagamento_servicos_compras(referencia {pk}, entidade{pk}, valor, estado, cliente {FK, NN})
* tranferencia(id_transferencia{pk}, valor, cliente_realiza{FK, NN}, cliente_recebe{FK, NN}) 
* funcionario(num_fun{PK}, nome, morada, email, telemovel, nif, password, gerente{FK})
* funcionario_cliente(num_fun{PK, FK1}, num_cli{PK, FK2})
* deposito(id_deposito{PK, AI}, valor, estado, funcionario{FK}, cliente{FK, NN})
* transacoes(id{PK, AI}, num_cli[FK}, descricao, valor)