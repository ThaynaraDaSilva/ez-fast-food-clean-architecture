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
kubectl create configmap ez-fast-food-config --from-env-file=.env -n ez-fast-food
kubectl create configmap ez-fast-food-config --from-env-file=.env -n ez-fast-food

// CONFIG MAP DO SCRIPT
kubectl create configmap ez-fast-food-sql-configmap --from-file=./src/main/resources/database.sql -n ez-fast-food
kubectl describe configmap ez-fast-food-sql-configmap -n ez-fast-food


kubectl delete pods --all -n ez-fast-food
kubectl delete deployment ez-fast-food-deployment -n ez-fast-food
kubectl delete service ez-fast-food-service -n ez-fast-food


//LOGS
kubectl logs <nome-do-pod> -n ez-fast-food



//
kubectl get configmap -n ez-fast-food



´´´kubectl get nodes´´´
´´´kubectl top pod´´´
´´´kubectl get deployment metrics-server -n kube-system´´´
´´´kubectl version --client´´´
´´´kubectl version --client´´´
´´´kubectl version --client´´´
´´´kubectl version --client´´´


	