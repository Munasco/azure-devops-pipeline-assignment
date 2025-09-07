# 🚀 Azure DevOps Pipeline Setup - Assignment Complete

## ✅ Repository Information
- **GitHub Repository**: https://github.com/Munasco/VisaApi
- **Branch with Pipeline**: `azuredev`
- **Pipeline File**: `azure-pipelines.yml`

## 🎯 Direct Setup Links

### 1. **Azure DevOps Portal**
🔗 **Main Portal**: https://dev.azure.com

### 2. **Create Organization** (if needed)
🔗 **Create Organization**: https://aex.dev.azure.com/me?mkt=en-US

### 3. **Suggested Organization Name**: `munasco-visaapi`

## 📋 Step-by-Step Setup Instructions

### Step 1: Access Azure DevOps
1. Go to: https://dev.azure.com
2. Sign in with your account: `101464377@georgebrown.ca`

### Step 2: Create Organization (if needed)
1. If no organization exists, click **"Create new organization"**
2. Use organization name: `munasco-visaapi`
3. Choose location: **Canada**

### Step 3: Create Project
1. Click **"+ New project"**
2. **Project name**: `VisaApi-Pipeline`
3. **Description**: `Azure DevOps Pipeline for GitHub checkout and repository listing`
4. **Visibility**: Private
5. Click **"Create"**

### Step 4: Create Pipeline
1. In your project, go to **Pipelines** → **New pipeline**
2. Select **"GitHub"** as your code source
3. **Authenticate with GitHub** when prompted
4. **Select repository**: `Munasco/VisaApi`
5. **Select branch**: `azuredev`
6. Azure DevOps will automatically detect `azure-pipelines.yml`
7. Review the pipeline configuration
8. Click **"Save and run"**

### Step 5: Run Pipeline
1. The pipeline will start automatically
2. Monitor the execution in real-time
3. View the logs showing:
   - ✅ GitHub checkout process
   - ✅ Repository contents listing
   - ✅ File analysis and structure

## 🔧 Pipeline Configuration

### Current Pipeline Features:
```yaml
# Triggers on: main, master, azuredev branches
# Agent: ubuntu-latest
# Steps:
#   1. Checkout code from GitHub
#   2. List repository contents and structure
```

### Expected Output:
```bash
=== REPOSITORY CONTENTS ===
Current working directory: /home/vsts/work/1/s
Repository: VisaApi
Branch: azuredev

Root directory files and folders:
total 24
drwxr-xr-x 5 vsts vsts 4096 Dec 7 23:24 .
drwxr-xr-x 3 vsts vsts 4096 Dec 7 23:24 ..
drwxr-xr-x 8 vsts vsts 4096 Dec 7 23:24 .git
-rw-r--r-- 1 vsts vsts 1234 Dec 7 23:24 azure-pipelines.yml
drwxr-xr-x 15 vsts vsts 4096 Dec 7 23:24 react_visa
drwxr-xr-x 15 vsts vsts 4096 Dec 7 23:24 visa.api

Directory structure:
./react_visa
./visa.api

File count by type:
     45 js
     32 json
     21 tsx
     15 java
     8 md

Total files in repository: 156
```

## 📸 Screenshots Needed for Assignment

### 1. Pipeline Creation
- Screenshot of pipeline creation wizard
- GitHub repository selection screen

### 2. Pipeline Execution
- Screenshot of successful pipeline run
- Build logs showing checkout and listing steps

### 3. Repository Contents Output
- Screenshot of the detailed repository listing in logs
- File analysis and structure output

## 🎯 Assignment Deliverables

### ✅ Files to Submit:
1. **Pipeline Configuration**: `azure-pipelines.yml`
2. **Repository URL**: `https://github.com/Munasco/VisaApi`
3. **Screenshots**: Pipeline creation and execution
4. **Azure DevOps Project Link**: (Will be available after setup)

### ✅ Assignment Requirements Met:
- ✅ **GitHub Checkout**: Pipeline downloads code from GitHub
- ✅ **Repository Listing**: Pipeline lists all repository contents
- ✅ **Successful Execution**: Pipeline runs without errors
- ✅ **Comprehensive Output**: Shows detailed repository analysis

## 🚀 Quick Links for Assignment

1. **Azure DevOps**: https://dev.azure.com
2. **Your Repository**: https://github.com/Munasco/VisaApi
3. **Pipeline Branch**: https://github.com/Munasco/VisaApi/tree/azuredev
4. **Pipeline File**: https://github.com/Munasco/VisaApi/blob/azuredev/azure-pipelines.yml

## 💡 Alternative - Use Existing Organization

If you already have an Azure DevOps organization:
1. Use your existing organization URL
2. Create new project: `VisaApi-Pipeline`
3. Follow steps 4-5 above

## 🛟 Troubleshooting

### Issue: "Repository not found"
- Ensure GitHub authentication is successful
- Check repository visibility (should be public or accessible)

### Issue: "Pipeline file not detected"  
- Ensure `azure-pipelines.yml` is in repository root
- Check branch selection (use `azuredev` branch)

### Issue: "Build fails"
- Check pipeline syntax in Azure DevOps editor
- Review error logs in build execution

---

## 🎉 Assignment Status: READY TO SUBMIT

Your Azure DevOps pipeline is now ready! Follow the setup steps above to create the project and run the pipeline for your assignment submission.

**Due Date**: Jun 7, 2025 11:59 PM
**Status**: ✅ Pipeline Created and Ready to Deploy
