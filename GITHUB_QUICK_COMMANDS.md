# 🚀 Quick GitHub Upload Commands (Windows)

## 🎯 One-Time Setup (Copy & Paste)

After creating repository on GitHub, run these commands in PowerShell in your project folder:

### Step 1: Configure Git User (First Time Only)
```powershell
# Run ONCE to configure your name and email
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

### Step 2: Add GitHub Remote & Push
```powershell
# Change to your project directory
cd "d:\codes\Google Android Dev\kot1"

# Add the remote (replace YOUR_USERNAME)
git remote add origin https://github.com/YOUR_USERNAME/smart-tasks.git

# Rename main branch
git branch -M main

# Push code to GitHub
git push -u origin main
```

---

## ✅ Verify Upload Worked

```powershell
# Check remote configuration
git remote -v

# Should show:
# origin  https://github.com/YOUR_USERNAME/smart-tasks.git (fetch)
# origin  https://github.com/YOUR_USERNAME/smart-tasks.git (push)
```

---

## 📝 Regular Workflow (After Initial Upload)

### Make Changes & Push
```powershell
cd "d:\codes\Google Android Dev\kot1"

# See what changed
git status

# Add all changes
git add .

# Commit with message
git commit -m "feat: Add new feature here"

# Push to GitHub
git push origin main
```

### Pull Latest Updates
```powershell
cd "d:\codes\Google Android Dev\kot1"
git pull origin main
```

---

## 🏷️ Create Releases for Play Store

Each time you submit to Play Store, create a release:

```powershell
# Tag release
git tag -a v1.0 -m "Initial release - April 2026"

# Tag another version later
git tag -a v1.1 -m "Version 1.1 - Fixed bugs"

# Push tags to GitHub
git push origin --tags
```

---

## 📊 Check Status Anytime

```powershell
cd "d:\codes\Google Android Dev\kot1"

# Show git status
git status

# Show recent commits
git log --oneline -10

# Show all branches
git branch -a

# Show all remotes
git remote -v
```

---

## ⚠️ Important: Update Git Config

**Optional but recommended** - Put your actual info:

```powershell
# Set your name globally (all projects)
git config --global user.name "Ms Kumar"
git config --global user.email "ms.kumar.developer05@gmail.com"

# Or just for this project
cd "d:\codes\Google Android Dev\kot1"
git config user.name "Ms Kumar"
git config user.email "ms.kumar.developer05@gmail.com"
```

---

## 🔐 SSH Setup (Optional - More Secure)

If you prefer SSH over HTTPS:

```powershell
# Generate SSH key (run once)
ssh-keygen -t ed25519 -C "ms.kumar.developer05@gmail.com"

# When prompted, press Enter to use default location
# When prompted for passphrase, press Enter twice for no passphrase

# Copy the public key
cat $env:USERPROFILE\.ssh\id_ed25519.pub

# Then:
# 1. Go to GitHub Settings → SSH and GPG keys
# 2. Click "New SSH key"
# 3. Paste the key and save

# After setup, use SSH URL for remote:
git remote add origin git@github.com:YOUR_USERNAME/smart-tasks.git
```

---

## 🎬 Example: Complete Upload in 5 Commands

```powershell
# 1. Navigate to project
cd "d:\codes\Google Android Dev\kot1"

# 2. Check what's going up
git status

# 3. Add remote (replace YOUR_USERNAME)
git remote add origin https://github.com/YOUR_USERNAME/smart-tasks.git

# 4. Set main branch
git branch -M main

# 5. Push to GitHub
git push -u origin main
```

**Done! 🎉**

---

## 🆘 Common Issues & Fixes

### ❌ "fatal: Not a git repository"
```powershell
# Make sure you're in the right folder
cd "d:\codes\Google Android Dev\kot1"
```

### ❌ "remote origin already exists"
```powershell
# Remove old remote
git remote remove origin

# Then add new one
git remote add origin https://github.com/YOUR_USERNAME/smart-tasks.git
```

### ❌ "Permission denied (publickey)"
- Make sure you're using HTTPS URL (has https://)
- Not SSH (git@github.com:...)

### ❌ "Updates were rejected"
```powershell
# Pull first, then push
git pull origin main
git push origin main
```

### ❌ "Uncommitted changes"
```powershell
# Commit everything first
git add .
git commit -m "Your message"
git push origin main
```

---

## 📝 What Gets Uploaded

✅ **Included:**
- All source code (`.kt`, `.xml`, `.java` files)
- Gradle configuration
- Documentation (README, DEVELOPER_GUIDE, etc.)
- Project settings

❌ **NOT Included** (Protected by .gitignore):
- `build/` folder (generated files)
- `my-release-key.jks` (keystore - NEVER uploaded!)
- `local.properties` (your PC settings)
- `.idea/` (IDE files)
- `.gradle/` (build cache)

---

## 🎓 Learning Git

Helpful resources:
- **GitHub Docs:** https://docs.github.com
- **Git Basics:** https://git-scm.com/book/en/v2
- **Interactive Tutorial:** https://learngitbranching.js.org/

---

**Ready? Let's go! 🚀**

1. Create repo on GitHub: https://github.com/new
2. Get the URL from GitHub
3. Replace YOUR_USERNAME in commands above
4. Copy & paste the 5 commands in PowerShell
5. Done!

Questions? See GITHUB_UPLOAD_GUIDE.md for complete guide.
