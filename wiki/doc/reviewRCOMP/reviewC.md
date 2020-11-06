RCOMP 2019-2020 Project - Sprint C review
=========================================
### Sprint master: 1171865 ###
(This file is to be created/edited by the sprint master only)
# 1. Sprint's backlog #
* T.1 - Development of the Central System application features regarding communications with Industrial Machines. (US4002)
* T.2 - Development of the Industrial Machines application regarding communications with the Central System. (US1011)
* T.3 - Development of the Industrial Machines application regarding communications with the Monitoring System. (US1012)
* T.4 - Development of the Monitoring System application regarding communications with Industrial Machines. (US6001)

# 2. Subtasks assessment #
One by one, each team member presents his/her outcomes to the team, the team assesses 		the accomplishment of the subtask backlog.
The subtask backlog accomplishment can be assessed as one of:

* 2.1. 1171865 - Development of the Industrial Machines application regarding communications with the Monitoring System. (US1012)
## Totally implemented with issues. ##
O simulador executa as duas threads e estabelece conexões UDP e TCP.
Apresenta um bloqueio nas conexões quando a thread com a conexão TCP (envio de mensages) está a operar e há uma requisição de informação por via do SMM.
Separadamente as conexões funcionam perfeitamente.
O bloqueio está a ser investigado.

* 2.2. 1171668 - Development of the Monitoring System application regarding communications with Industrial Machines. (US6001)
## Totally implemented with no issues. ##

* 2.3. 1181436 - Development of the Central System application features regarding communications with Industrial Machines. (US4002)
## Totally implemented with no issues. ##

* 2.4. 1190293 - Development of the Industrial Machines application regarding communications with the Central System. (US1011)
## Totally implemented with issues ##
O simulador executa as duas threads e estabelece conexões UDP e TCP.
Apresenta um bloqueio nas conexões quando a thread com a conexão TCP (envio de mensages) está a operar e há uma requisição de informação por via do SMM.
Separadamente as conexões funcionam perfeitamente.
O bloqueio está a ser investigado.
