# Azure DevOps Pipeline Guide

## Assignment: Simple GitHub Checkout Pipeline

This repository contains a complete Azure DevOps pipeline that demonstrates:

1. ✅ **Checkout code from GitHub repository**
2. ✅ **List the contents of the repository**
3. ✅ **Additional repository analysis and information**

## 📁 Files Created

- `azure-pipelines.yml` - Main pipeline configuration file
- `Azure-DevOps-Pipeline-Guide.md` - This guide (optional for submission)

## 🚀 How to Set Up the Pipeline in Azure DevOps

### Prerequisites

1. **Azure DevOps Account**: Sign up at https://dev.azure.com
2. **GitHub Repository**: Your code should be in a GitHub repository
3. **Service Connection**: Connection between Azure DevOps and GitHub

### Step-by-Step Setup

#### 1. Create Azure DevOps Project

1. Go to https://dev.azure.com
2. Sign in with your Microsoft account
3. Create a new organization (if you don't have one)
4. Create a new project:
   - Project name: `VisaApi-Pipeline` (or any name you prefer)
   - Visibility: Private or Public
   - Version control: Git

#### 2. Connect to GitHub Repository

1. In your Azure DevOps project, go to **Pipelines** → **New pipeline**
2. Select **GitHub** as your code location
3. You may need to authorize Azure DevOps to access your GitHub account
4. Select your repository (`VisaApi` or the repository containing the `azure-pipelines.yml` file)
5. Azure DevOps will automatically detect the `azure-pipelines.yml` file

#### 3. Review and Run Pipeline

1. Azure DevOps will show a preview of your pipeline
2. Click **Save and run**
3. Commit the pipeline file to your repository (if not already there)
4. The pipeline will start running automatically

#### 4. Alternative: Manual Pipeline Creation

If you prefer to set up manually:

1. Go to **Pipelines** → **New pipeline**
2. Select **Azure Repos Git** or **GitHub**
3. Select your repository
4. Choose **Existing Azure Pipelines YAML file**
5. Select the branch and path to your `azure-pipelines.yml` file
6. Save and run

## 📋 Pipeline Overview

### What the Pipeline Does

The `azure-pipelines.yml` file contains a comprehensive pipeline with the following stages:

#### Stage 1: CheckoutAndList
1. **Checkout Code**: Downloads the latest code from GitHub
2. **Pipeline Information**: Displays build details and metadata
3. **Repository Contents**: Lists all files and directories
4. **Repository Analysis**: Shows file statistics and breakdown
5. **Project Structure**: Identifies common project files
6. **Git Information**: Displays commit history and branch info
7. **Environment Info**: Shows build agent specifications

### Key Features

- **Triggers**: Runs on pushes to `main`, `master`, or `develop` branches
- **Pull Request Triggers**: Also runs on PRs to main branches
- **Cross-Platform**: Uses Ubuntu latest agent (can be changed to Windows/macOS)
- **Detailed Logging**: Comprehensive output for debugging and analysis
- **Flexible**: Easy to extend with additional steps

### Pipeline Triggers

```yaml
# Automatic triggers
trigger:
  branches:
    include:
      - main
      - master
      - develop

# Pull request triggers
pr:
  branches:
    include:
      - main
      - master
      - develop
```

## 📊 Expected Pipeline Output

When the pipeline runs, you'll see output sections like:

```
=== AZURE DEVOPS PIPELINE INFORMATION ===
Pipeline Name: VisaApi
Build Number: 20231205.1
Build ID: 123
Source Branch: main
Repository: VisaApi
Commit ID: abc123...
============================================

=== ROOT DIRECTORY CONTENTS ===
total 16
drwxr-xr-x  4 vsts  vsts   128 Dec  5 10:30 .
drwxr-xr-x  3 vsts  vsts    96 Dec  5 10:30 ..
drwxr-xr-x  8 vsts  vsts   256 Dec  5 10:30 .git
-rw-r--r--  1 vsts  vsts  5234 Dec  5 10:30 azure-pipelines.yml
drwxr-xr-x  15 vsts vsts   480 Dec  5 10:30 react_visa
drwxr-xr-x  15 vsts vsts   480 Dec  5 10:30 visa.api

=== REPOSITORY ANALYSIS ===
Total files in repository: 156
File types breakdown:
     45 js
     32 json
     21 css
     18 md
     15 tsx
...
```

## 🔧 Customization Options

### Modify Agent Pool
```yaml
pool:
  vmImage: 'windows-latest'  # or 'macOS-latest'
```

### Add More Triggers
```yaml
trigger:
  branches:
    include:
      - '*'  # All branches
  tags:
    include:
      - v*   # Version tags
```

### Add Build Steps
You can extend the pipeline by adding more tasks after the listing steps:

```yaml
- task: NodeTool@0
  displayName: 'Install Node.js'
  inputs:
    versionSpec: '18.x'

- script: npm install
  displayName: 'Install dependencies'
```

## 📤 Submission Requirements

For your assignment submission, you need to provide:

1. ✅ **Pipeline File**: `azure-pipelines.yml`
2. ✅ **Screenshots**: Pipeline run results showing:
   - Successful pipeline execution
   - Repository contents being listed
   - Build logs showing checkout and listing steps
3. ✅ **Repository Link**: GitHub repository URL containing the pipeline
4. ✅ **Azure DevOps Project**: Link to your Azure DevOps project (if accessible)

## 📸 What to Screenshot

1. **Pipeline Overview**: The pipeline runs page showing successful execution
2. **Build Logs**: Detailed logs showing:
   - GitHub checkout step
   - Repository contents listing
   - File analysis output
3. **Pipeline Definition**: The YAML file content in Azure DevOps
4. **Repository Connection**: Settings showing GitHub integration

## 🛟 Troubleshooting

### Common Issues

1. **Pipeline doesn't trigger**: Check your trigger configuration and branch names
2. **GitHub connection fails**: Verify service connections in Project Settings
3. **Permission errors**: Ensure proper GitHub permissions for Azure DevOps
4. **YAML syntax errors**: Validate YAML formatting (proper indentation)

### Useful Azure DevOps URLs

- **Main Portal**: https://dev.azure.com
- **Documentation**: https://docs.microsoft.com/en-us/azure/devops/
- **YAML Schema**: https://docs.microsoft.com/en-us/azure/devops/pipelines/yaml-schema

## 🎯 Assignment Success Criteria

✅ **Pipeline Creation**: Successfully created `azure-pipelines.yml`  
✅ **GitHub Integration**: Pipeline connects to and checks out from GitHub  
✅ **Repository Listing**: Pipeline lists repository contents  
✅ **Successful Execution**: Pipeline runs without errors  
✅ **Documentation**: Clear screenshots and evidence of working pipeline  

## 📚 Next Steps

After completing this basic pipeline, you can extend it with:

- **Testing**: Add unit tests, integration tests
- **Building**: Compile/build your application
- **Deployment**: Deploy to staging/production environments
- **Notifications**: Email or Slack notifications on build status
- **Artifacts**: Publish build artifacts for download

---

**Good luck with your assignment!** 🚀

If you have any questions about setting up or running the pipeline, refer to the official Azure DevOps documentation or reach out for help.
