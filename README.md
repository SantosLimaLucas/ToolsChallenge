# ToolsChallenge
API capaz de registrar, consultar e estornar pagamentos.

# Requisições

## *registrar pagamento
    Esta requisição é capaz de registrar um novo pagamento.
    É executada na URL: POST -> RAIZ/pagamentos (localhost:8080/pagamentos)
    
    Exemplo de Json esperado no Body da requisição:
    
        {
          "transacao": {
            "cartao": "13264589754653215",
            "id": "1",
            "descricao": {
              "valor": "560.00",
              "dataHora": "01/05/2021 18:30:00",
              "estabelecimento": "Pet Shop Brasil"
            },
            "formaPagamento": {
              "tipo": "PARCELADO LOJA",
              "parcelas": "1"
            }
          }
        }
        obs.: Os tipos de pagamento aceitos são: "PARCELADO LOJA", "PARCELADO EMISSOR" e "AVISTA". 
## *listagem de todos os pagamentos 
    Esta requisição retorna a lista de todos os pagamentos registrados.
    É executada com a URL: GET -> RAIZ/pagamentos (localhost:8080/pagamentos)
    Exemplo de resposta:
    [
      {
          "cartao": "111111111",
          "id": "333333",
          "descricao": {
              "valor": "560.00",
              "dataHora": "01/05/2021 18:30:00",
              "estabelecimento": "Padaria Delikata",
              "nsu": "611982799",
              "codigoAutorizacao": "491265",
              "status": "CANCELADO"
          },
          "formaPagamento": {
              "tipo": "PARCELADO_LOJA",
              "parcelas": "1"
          }
      },
      {
          "cartao": "4251253658957",
          "id": "2",
          "descricao": {
              "valor": "10.00",
              "dataHora": "01/05/2021 18:30:00",
              "estabelecimento": "Supermercados Santarém",
              "nsu": "408218658",
              "codigoAutorizacao": "796350",
              "status": "AUTORIZADO"
          },
          "formaPagamento": {
              "tipo": "AVISTA",
              "parcelas": "1"
          }
      },
      {
          "cartao": "3625487952542",
          "id": "3",
          "descricao": {
              "valor": "1000.00",
              "dataHora": "01/05/2021 18:30:00",
              "estabelecimento": "Supermercados Santarém",
              "nsu": "783170766",
              "codigoAutorizacao": "674998",
              "status": "AUTORIZADO"
          },
          "formaPagamento": {
              "tipo": "PARCELADO_LOJA",
              "parcelas": "4"
          }
      }
    ]
    
## *listar pagamento pelo ID
    Esta requisição GET retorna o pagamento correspondente ao id informando como parâmetro.
    É executada através da URL: GET -> RAIZ/pagamentos/{id} (localhost:8080/pagamentos/2)
    Exemplo de resposta:
    {
      "cartao": "4251253658957",
      "id": "2",
      "descricao": {
          "valor": "10.00",
          "dataHora": "01/05/2021 18:30:00",
          "estabelecimento": "Supermercados Santarém",
          "nsu": "408218658",
          "codigoAutorizacao": "796350",
          "status": "AUTORIZADO"
      },
      "formaPagamento": {
          "tipo": "AVISTA",
          "parcelas": "1"
      }
    }
    
## *realizar estorno
    Esta requisição recebe como parâmetro um id de pagamento.     
    É executada na URL: PATCH -> RAIZ/pagamentos/{id}/estorno. (localhost:8080/pagamentos/1/estorno)
    Caso seja encontrado um pagamento com o id informado, o status é alterado para CANCELADO e o pagamento é retornado.
    Exemplo de retorno:
    {
      "cartao": "111111111",
      "id": "1",
      "descricao": {
          "valor": "560.00",
          "dataHora": "01/05/2021 18:30:00",
          "estabelecimento": "Lucas 2 Teste",
          "nsu": "611982799",
          "codigoAutorizacao": "491265",
          "status": "CANCELADO"
      },
      "formaPagamento": {
          "tipo": "PARCELADO_LOJA",
          "parcelas": "1"
      }
    }
