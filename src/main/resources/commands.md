# Preparação de ambiente

## OBS: permitido somente 1 node no docker desktop. 1 node para N pods
1. Instalar Docker Desktop
2. Ativar Kubernetes no Docker Desktop
Comando para verificar ativação ta ok:
	´´´kubectl config get-contexts´´´
3. Instalar kubectl (se necessário)
	Opção binário: https://kubernetes.io/releases/download/#binaries
	https://dl.k8s.io/release/v1.22.0/bin/windows/amd64/kubectl.exe
	Criei pasta e movi o exe para: "C:\TOOLS\k8s\kubectl.exe"
	Adicionei o caminho "C:\TOOLS\k8s\kubectl.exe" ao path em variaveis de ambiente
	
4. kubectl apply -f https://github.com/kubernetes-sigs/metrics-server/releases/latest/download/components.yaml

	
5. Configurar aplicação para Docker
6. Build docker image
7. Preparar manifests para k8s
8. Deploy no k8s
9. Escalar com k8s:
	kubectl scale deployment ez-fastfood-deployment --replicas=5
	
Comando para verificar se ta ok via cmd
´´´kubectl version --client´´´

## how to

#### Build da imagem

docker-compose up --build 
//docker build -t ez-fastfood:latest .

kubectl create namespace ez-fast-food
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml

kubectl logs metrics-server-5584545f46-bstvh -n kube-system
kubectl top pod -n ez-fast-food

//parar pods
kubectl scale deployment ez-fast-food-deployment --replicas=0 -n ez-fast-food
//subir pods
kubectl scale deployment ez-fast-food-deployment --replicas=2 -n ez-fast-food


//criacao de configmap do ENV dentro do cluster
kubectl create configmap ez-fast-food-configmap-app --from-env-file=.env -n ez-fast-food
kubectl describe configmap ez-fast-food-configmap-app -n ez-fast-food


// CONFIG MAP DO SCRIPT
kubectl create configmap ez-fast-food-configmap-sql --from-file=./src/main/resources/database.sql -n ez-fast-food
kubectl describe configmap ez-fast-food-configmap-sql -n ez-fast-food


kubectl delete pods --all -n ez-fast-food
kubectl delete deployment ez-fast-food-deployment -n ez-fast-food
kubectl delete service ez-fast-food-service -n ez-fast-food


//LOGS
kubectl logs ez-fast-food-deployment-696c587d5d-bjfff -n ez-fast-food



//
kubectl get configmap -n ez-fast-food
kubectl apply -f app-config.yaml
kubectl apply -f db-config.yaml



//banco de dados
kubectl apply -f postgres-pvc.yaml
kubectl apply -f postgres-deployment.yaml
kubectl apply -f postgres-service.yaml



´´´kubectl get nodes´´´
´´´kubectl top pod´´´
´´´kubectl get deployment metrics-server -n kube-system´´´
´´´kubectl version --client´´´


## RECRIAÇÃO DO AMBIENTE
### Excluir configmaps
kubectl delete configmap ez-fast-food-configmap-app -n ez-fast-food
kubectl delete configmap ez-fast-food-configmap-sql -n ez-fast-food

### Excluir pods
kubectl delete pods --all -n ez-fast-food 

//kubectl delete pod <nome-do-pod> -n ez-fast-food

### Excluir deployment (app e bd)
kubectl delete deployment ez-fast-food-deployment -n ez-fast-food
kubectl delete deployment postgres-deployment -n ez-fast-food

### Excluir service (app e bd)
kubectl delete service ez-fast-food-service -n ez-fast-food
kubectl delete service postgres-service -n ez-fast-food

### Criar configmaps (app e bd)
kubectl create configmap ez-fast-food-configmap-app --from-env-file=.env -n ez-fast-food
kubectl create configmap ez-fast-food-configmap-sql --from-file=./src/main/resources/database.sql -n ez-fast-food

kubectl apply -f k8s/configmap-app.yaml   # For application ConfigMap
kubectl apply -f k8s/configmap-sql.yaml   # For SQL ConfigMap


### Criar deployment (app e bd)
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/postgres-deployment.yaml

### Criar service (app e bd)
kubectl apply -f k8s/service.yaml
kubectl apply -f k8s/postgres-service.yaml


### list

kubectl get pods -n ez-fast-food

kubectl get configmap -n ez-fast-food

kubectl describe configmap ez-fast-food-sql-configmap -n ez-fast-food


### logs
kubectl logs <nome-pod> -n ez-fast-food

kubectl logs <nome-pod> -n ez-fast-food


kubectl logs ez-fast-food-deployment-6cdcf85886-cqm9p -n ez-fast-food
kubectl describe pod ez-fast-food-deployment-7d645fd448-2jpj5 -n ez-fast-food





kubectl delete configmap ez-fast-food-configmap-app -n ez-fast-food
kubectl delete configmap ez-fast-food-configmap-sql -n ez-fast-food


kubectl delete pods --all -n ez-fast-food 


kubectl delete deployment ez-fast-food-deployment -n ez-fast-food
kubectl delete deployment ez-fast-food-db-deployment -n ez-fast-food



kubectl delete service ez-fast-food-service -n ez-fast-food
kubectl delete service postgres-service -n ez-fast-food

kubectl delete hpa ez-fast-food-hpa -n ez-fast-food


kubectl create configmap ez-fast-food-configmap-app --from-env-file=.env -n ez-fast-food
kubectl create configmap ez-fast-food-configmap-sql --from-file=./src/main/resources/database.sql -n ez-fast-food


kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/postgres-deployment.yaml

### Criar service (app e bd)
kubectl apply -f k8s/service.yaml
kubectl apply -f k8s/postgres-service.yaml


kubectl create configmap ez-fast-food-config --from-env-file=.env -n ez-fast-food
kubectl create configmap ez-fast-food-configmap-sql --from-file=./src/main/resources/database.sql -n ez-fast-food --dry-run=client -o yaml | kubectl apply -f -


kubectl apply -f k8s/postgres-deployment.yaml




# 28/09

## BUILD AND PUSH IMAGE

```
docker login -u dasilvathaynara

<secret>

docker build -t dasilvathaynara/ez-fast-food-api:latest .
docker push dasilvathaynara/ez-fast-food-api:latest


```

```

docker build -t your-dockerhub-username/your-app-name:tag .
kubectl create namespace ez-fast-food

kubectl create configmap ez-fast-food-configmap-app --from-env-file=.env -n ez-fast-food

kubectl create configmap ez-fast-food-configmap-sql --from-file=./src/main/resources/database.sql -n ez-fast-food

kubectl apply -f k8s/ez-fast-food-configmap-app.yaml


kubectl apply -f k8s/postgres-pvc.yaml -n ez-fast-food


kubectl apply -f k8s/postgres-deployment.yaml -n ez-fast-food

kubectl apply -f k8s/postgres-service.yaml -n ez-fast-food

kubectl apply -f k8s/deployment.yaml

kubectl apply -f k8s/service.yaml


kubectl apply -f k8s/hpa.yaml



kubectl top pod -n ez-fast-food


kubectl apply -f k8s

kubectl get pod -n ez-fast-food -o custom-columns='NAME:.metadata.name,CPU_REQUESTS:.spec.containers[*].resources.requests.cpu,CPU_LIMITS:.spec.containers[*].resources.limits.cpu,MEMORY_REQUESTS:.spec.containers[*].resources.requests.memory,MEMORY_LIMITS:.spec.containers[*].resources.limits.memory' && kubectl top pod -n ez-fast-food


kubectl get hpa -n ez-fast-food
kubectl get hpa -n <namespace>
kubectl describe hpa <hpa-name> -n <namespace>

kubectl describe hpa ez-fast-food-hpa -n ez-fast-food

EXCUTAR VIA POWERSHELL
while ($true) { kubectl get hpa -n ez-fast-food; Start-Sleep -Seconds 5 }


kubectl get pods -n ez-fast-food -o custom-columns=POD:.metadata.name,CPU_REQUEST:.spec.containers[*].resources.requests.cpu,MEMORY_REQUEST:.spec.containers[*].resources.requests.memory,CPU_LIMIT:.spec.containers[*].resources.limits.cpu,MEMORY_LIMIT:.spec.containers[*].resources.limits.memory


while ($true) { kubectl get pods -n ez-fast-food -o custom-columns=POD:.metadata.name,CPU_REQUEST:.spec.containers[*].resources.requests.cpu,MEMORY_REQUEST:.spec.containers[*].resources.requests.memory,CPU_LIMIT:.spec.containers[*].resources.limits.cpu,MEMORY_LIMIT:.spec.containers[*].resources.limits.memory; Start-Sleep -Seconds 2 }






```

kubectl logs ez-fast-food-deployment-6fbb55b686-5h5v9 -n ez-fast-food

kubectl exec -it ez-fast-food-deployment-6fbb55b686-5h5v9 -n ez-fast-food -- env

kubectl delete pod <pod-name> -n <namespace>

kubectl delete pod ez-fast-food-deployment-95dfbf447-lc9zx -n ez-fast-food 


kubectl logs ez-fast-food-deployment-7d645fd448-k2cmq -n ez-fast-food
kubectl exec -it ez-fast-food-deployment-7d645fd448-k2cmq -- /bin/sh


7ad5ecff33da

kubectl get deployments -n ez-fast-food

kubectl get pods -n ez-fast-food


comando para obter a capacidade do node
kubectl describe node docker-desktop 








	