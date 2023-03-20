pipeline {

    options {
        buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
    }

    agent any

    tools {
        maven 'maven_3.8.7'
    }
    stages {
	    stage('Printing Hello World') {
            steps {
                echo 'Hello World'
            }
        }
        stage('Code Compilation') {
            steps {
                echo 'code compilation is starting'
                sh 'mvn clean compile'
				echo 'code compilation is completed'
            }
        }
        stage('Code Package') {
            steps {
                echo 'code packing is starting'
                sh 'mvn clean package'
				echo 'code packing is completed'
            }
        }
        stage('Building & Tag Docker Image') {
            steps {
                echo 'Starting Building Docker Image'
                sh 'docker build -t prashantmkoredevops/hotel-ms:yourtag .'
                sh  'docker build -t prashant .'
                echo 'Completed  Building Docker Image'
            }
        }
        stage('Printing docker images') {
            steps {
                echo 'Printing docker images on linux'
                sh 'docker images'
            }
        }
        stage(' Docker push to Docker Hub') {
           steps {
              script {
                 withCredentials([string(credentialsId: 'dockerhubCred', variable: 'dockerhubCred')]){
                 sh 'docker login docker.io -u prashantmkoredevops -p ${dockerhubCred}'
                 echo "Push Docker Image to DockerHub : In Progress"
                 sh 'docker push prashantmkoredevops/hotel-ms:yourtag'
                 echo "Push Docker Image to DockerHub : In Progress"
                 sh 'whoami'
                 }
              }
            }
        }
                stage(' Docker Image Push to Amazon ECR') {
                   steps {
                      script {
                         withDockerRegistry([credentialsId:'ecr:ap-south-1:ecr-credentials', url:"https://807515595671.dkr.ecr.ap-south-1.amazonaws.com"]){
                         sh """
                         echo "List the docker images present in local"
                         docker images
                         echo "Tagging the Docker Image: In Progress"
                         docker tag prashant:latest 807515595671.dkr.ecr.ap-south-1.amazonaws.com/prashant:latest
                         echo "Tagging the Docker Image: Completed"
                         echo "Push Docker Image to ECR : In Progress"
                         docker push 807515595671.dkr.ecr.ap-south-1.amazonaws.com/prashant:latest
                         echo "Push Docker Image to ECR : Completed"
                         """
                         }
                      }
                   }
                }
        stage('Images remove from jenkins') {
            steps {
                echo 'Deleting images from jenkins server'
				sh 'docker rmi ubuntu:latest'
            }
        }
    }
}