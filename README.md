# rest-adi
Repositório para Disciplina de ADI 

REST Api de ordens de serviço desenvolvido em Java/Spring.

Banco de dados na pasta /src/main/resources.

## Rotas

### Clientes
GET - https://osworks-spring-cloud-osworks-api.azuremicroservices.io/api/clientes - GET ALL </br>
GET - https://osworks-spring-cloud-osworks-api.azuremicroservices.io/api/clientes/{clienteId} - GET ONE </br>
PUT - https://osworks-spring-cloud-osworks-api.azuremicroservices.io/api/clientes/{clienteId} - UPDATE ONE </br>
DEL - https://osworks-spring-cloud-osworks-api.azuremicroservices.io/api/clientes/{clienteId} - DELETE ONE </br>
POST - https://osworks-spring-cloud-osworks-api.azuremicroservices.io/api/clientes - INSERT ONE </br>
     - body: { </br>
                "nome": <nome>, </br>
                "senha": <senha>, </br>
                "email": <email>, </br>
                "telefone": <telefone> </br>
            } </br>

### Ordens de Serviço
GET -   https://osworks-spring-cloud-osworks-api.azuremicroservices.io/api/ordens-servico - GET ALL </br>
GET -   https://osworks-spring-cloud-osworks-api.azuremicroservices.io/api/ordens-servico/{ordemServicoId} - GET ONE </br>
PUT -   https://osworks-spring-cloud-osworks-api.azuremicroservices.io/api/ordens-servico/{ordemServicoId}/finalizacao - UPDATE (finaliza a O.S.) </br>
POST -  https://osworks-spring-cloud-osworks-api.azuremicroservices.io/api/ordens-servico/{ordemServicoId} - INSERT ONE </br>
     -  body: { </br>
                "cliente": <clienteID>, </br>
                "descricao": <descrição>, </br>
                "preco": <preço> </br>
              } </br>
  
<table>
  <tr>
    <td><img src="assets/clientes.jpg" width=500 height=520></td>
    <td><img src="assets/ordens-servico.jpg" width=500 height=520></td>
  </tr>
 </table>
