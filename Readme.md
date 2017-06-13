# Spring Boot on Bluemix with IBM Continuous Delivery

The goal of this lab is to demonstrate deploying a simple Java web application to Bluemix with a DevOps pipeline for managing the application lifecycle. 

As part of this deployment, several actions are happening automatically through the use of configuration files found in the [.bluemix](.bluemix) folder. The two primary actions that are automatically configured to occur are deploying two different microservices from two different repositories and integrating multiple Bluemix services into the deployed applications. 

## Table of Contents

- [Prerequisites](https://github.com/IBM/spring-boot-continuous-delivery/blob/master/Readme.md#prerequisites)
- [Steps](https://github.com/IBM/spring-boot-continuous-delivery/blob/master/Readme.md#steps)
- [Access Cloudant DB service](https://github.com/IBM/spring-boot-continuous-delivery/blob/master/Readme.md#cloudant-db)
- [Deployment Configuration](https://github.com/IBM/spring-boot-continuous-delivery/blob/master/Readme.md#deployment-configuration)
- [Associated Repositories](https://github.com/IBM/spring-boot-continuous-delivery/blob/master/Readme.md#associated-repositories)

## Prerequisites

1. A Bluemix account is required to complete this lab.

    To sign up for a new account visit [Bluemix.net](https://console.ng.bluemix.net/ "Welcome to Bluemix")

2. A Slack team is an **optional** requirement to complete this lab.

    To sign up for a new team visit [slack.com](https://slack.com/)

## Steps

1. Log into the [Bluemix console](https://console.ng.bluemix.net/) and create a [Cloudant instance](https://console.ng.bluemix.net/catalog/services/cloudant-nosql-db/?taxonomyNavigation=services) named `sample-java-cloudant-cloudantNoSQLDB`.

2. Click the following Deploy to Bluemix button to load the initial toolchain setup:

    [![Deploy to Bluemix](https://github.com/IBM/container-journey-template/blob/master/images/button.png)](https://bluemix.net/deploy?repository=https://github.com/IBM/spring-boot-continuous-delivery)

3. Once the Tool Integrations have loaded, click on each of the four different integrations to see what settings are available to be changed. 

    **Note:** that the fields for GitHub, Eclipse Orion Web IDE, and Delivery Pipeline are automatically populated with data and no input should be required by the user unless changes to what was populated are desired.

    <img src="media/BluemixToolIntegrations.png" width="500">
    
    **Optional:** Slack Integration. Skip to Step 4 if you choose not to configure it. 
    
    Slack Integration allows your team to be notified of events occuring in your Delivery Pipeline. To configure this integration, create an incoming webhook for your Slack team using the [Slack API](https://api.slack.com/incoming-webhooks). Once created, fill in the three fields listed under the Slack Integration.
    
    <img src="media/BluemixSlackIntegration.png" width="500">

4. Click the "Create" button in the bottom right area of the window to instruct Bluemix to create a toolchain for you using the defined settings.

    <img src="media/BluemixCreateButton.png" width="200">

5. The page that loads after clicking create is the application's Toolchain. Here is the overview of all of the integrated services, the flow of the project, and easy navigation to each of the individual components of the application.

    <img src="media/BluemixToolchainView.png" width="500">

6. As soon as the "Create" button was pressed, Bluemix went to work deploying the application via the defined pipeline configurations. Click on the "Delivery Pipeline" box in the Toolchain to view the code deployment pipeline.

    <img src="media/DeliveryPipeline.png" width="200">

7. The pipeline page will dynamically update with the status of each step in the pipeline. When the deployment has finished running the bar on the top of the "Deploy Stage" will turn green.

In Progress                |  Finished
:-------------------------:|:-------------------------:
![](media/PipelineInProgress.png)  |  ![](media/PipelineFinished.png)

8. Once the "Deploy Stage" has turned green, indicating the job is complete, the link to the deployed application can be found in the mid-section of the "Deploy Stage" card.

    <img src="media/PipelineAppLink.png" width="300">

9. Upon clicking the application's URL, a new tab will open to the application's main page.

Create a New Account                |  Account Created
:-------------------------:|:-------------------------:
![](media/AccountImage1.png)  |  ![](media/AccountImage2.png)

-----

## Cloudant DB

The following steps will show how to access the account that was just created in the Cloudant DB database

1. Return to [Bluemix](https://console.ng.bluemix.net/ "Welcome to Bluemix") and open the [Services Dashboard](https://console.ng.bluemix.net/dashboard).

    <img src="media/FindService2.png" width="200">

2. Once on the Services Dashboard, locate your Cloudant DB and click on it to open the service description page.

    <img src="media/FindService3.png" width="700">

3. Click on the "LAUNCH" button near the top right of the page to load into the Cloudant DB dashboard.

4. Once the Cloudant DB dashboard has finished loading, select the databases tab on the left.

    <img src="media/OpenDB2.png" width="200">

5. Click on the "Account" database. On the right you will see the account that was created from the web app above.

    <img src="media/DBEntry1.png" width="300">

6. Click on the database entry to view more details.

    <img src="media/DBEntry2.png" width="500">

-----

# Deployment Configuration

In this application we use several different files that each server an important part of customizing our Bluemix deployment. Below we will give a brief overview of each file's function.

## toolchain.yml

File location: [.bluemix/toolchain.yml](.bluemix/toolchain.yml)

The toolchain.yml file is used to define the desired application name, integrated services, descriptions, environment properties, and more. This file is what pulls in the pipeline.yml, deploy.json, and custom Toolchain image used in this application.

For our application we have integrated GitHub as a code repository as well as for issue tracking, Eclipse Orian Web IDE for editing code via the web interface, and a pipeline to deploy the application from the integrated GitHub repository.

## deploy.json

File location: [.bluemix/deploy.json](.bluemix/deploy.json)

The deploy.json file is what allows user customization of the Toolchain via the UI before the user clicks deploy but after the user has clicked the "Deploy to Bluemix" button from the GitHub repository.

For our application we have configured the deploy.son file to capture and require three fields that will be used as environment variables by the toolchain.yml: selected-region, selected-organization, and selected-space. We have created a three column form that allows users to select fields for each of the afore mentioned variables if the defaults are not desired. Finally, we have called a helper function to validate the input variables.

## pipeline.yml

File location: [.bluemix/pipeline.yml](.bluemix/pipeline.yml)

The pipeline.yml file is used to configure the Bluemix Pipeline for an application. A toolchain can have multiple pipeline files. One way to rename the pipeline.yml files is to add a prefix to the pipeline.yml file's name: example-pipeline.yml. In the pipeline.yml file we can specify things such as build steps, environment variables, custom scripts and instructions, etc.

For our application we have configured the pipeline.yml to build our maven project to a custom location and then deploy the built .war file from the custom location. Additionally, in our pipeline.yml file we have specified the application memory limit using the `cf` command:

```cf push "${CF_APP}" -p "springboot-demo.war" -m 256M```

-----

# Associated Repositories

One of the primary aspects of this lab is to demonstrate simplified deployment of microservices from multiple repositories. As part of the setup for the different microservices a unique deployment pipeline cis reated for and connected to each of the individual microservice's repositories. By creating a different pipeline for each repository we are enabling each service to have its code updated and re-deployed completely independent of the operation of the other microservice; that is to say, by having two pipelines we can update the code for one microservice and the other microservice will be completely unaffected and unaware anything happened.

- Admin Dashboard Microservice: [https://github.com/IBM/spring-boot-continuous-delivery-admin](https://github.com/IBM/spring-boot-continuous-delivery-admin)

   **Note:** No direct interaction with the associated repositories is required. All of the deployment interaction is done through the current repository.
