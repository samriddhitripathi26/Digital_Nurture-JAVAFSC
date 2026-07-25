
## Lab 1 — Git setup, notepad++ as default editor, first commit

**Step 1: Configure Git**
```bash
git --version                                    # confirms Git Bash is installed

git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"

git config --global --list                       # verify it stuck
```

**Step 2: Make notepad++ the default Git editor**
```bash
notepad++                                         # if this fails, notepad++.exe isn't on PATH yet
```
Add notepad++'s install folder (e.g. `C:\Program Files\Notepad++`) to your **user PATH** environment variable (Control Panel → System → Advanced system settings → Environment Variables → edit the `Path` user variable → Add), then close and reopen Git Bash.

```bash
notepad++                                         # should now open

# create a shell alias/function so 'notepad++' works cleanly from bash
notepad++ ~/.bashrc
# add this line inside the file, save, close:
alias notepad++="'/c/Program Files/Notepad++/notepad++.exe'"

git config --global core.editor "notepad++ -multiInst -notabbar -nosession -noPlugin"

git config -e                                     # -e = open the config in your editor; confirms it's notepad++
```

**Step 3: Create a repo and push your first file**
```bash
mkdir GitDemo && cd GitDemo
git init                                          # initializes the GitDemo repo

ls -la                                            # -a shows the hidden .git folder — confirms it's a Git working directory

echo "Welcome to Git" > welcome.txt                # creates welcome.txt with content
ls                                                 # confirm the file exists
cat welcome.txt                                    # confirm the content

git status                                         # welcome.txt shows as untracked

git add welcome.txt                                # stages it for the next commit

git commit                                         # opens notepad++ (your configured editor) for a multi-line commit message
                                                    # write a message, save, close the editor to complete the commit

git status                                         # should now say "nothing to commit, working tree clean"
```

**Connect to the remote you created on GitLab/GitHub:**
```bash
git remote add origin <remote-url>
git pull origin master --allow-unrelated-histories   # only needed if the remote already has a README/license
git push origin master
```

---

## Lab 2 — .gitignore

**Goal:** create a `.log` file and a `log` folder, then make Git ignore both.

```bash
cd GitDemo                                         # (or wherever your repo from Lab 1 lives)

echo "sample log entry" > app.log                  # a .log file
mkdir log && echo "another log" > log/debug.log     # a log/ folder with a file in it

git status                                          # both app.log and log/ show as untracked — this is the "before" state
```

**Create/update `.gitignore`:**
```bash
notepad++ .gitignore
```
Add these two lines, save, close:
```
*.log
log/
```

```bash
git status                                          # app.log and log/ no longer appear as untracked —
                                                      # confirms .gitignore is working

git add .gitignore
git commit -m "Add .gitignore to exclude .log files and log folder"
git push origin master
```

If `app.log` or anything in `log/` was **already tracked** before you added the ignore rule, `.gitignore` won't touch it — you'd need to explicitly untrack it first: `git rm --cached app.log`.

---

## Lab 3 — Branching and merging

**Branching:**
```bash
git branch GitNewBranch                             # create the branch

git branch -a                                        # list local (and -a includes remote) branches;
                                                       # the branch with "*" next to it is your current one — still master here

git checkout GitNewBranch                             # switch to it
# (or the newer equivalent: git switch GitNewBranch)

echo "some feature content" > feature.txt
git add feature.txt
git commit -m "Add feature.txt on GitNewBranch"

git status                                            # working tree clean on GitNewBranch
```

**Merging:**
```bash
git checkout master                                    # switch back to master

git diff master GitNewBranch                           # command-line diff between the two branches

git difftool --tool=p4merge master GitNewBranch         # visual diff via P4Merge
                                                          # (needs P4Merge installed and configured as a difftool first —
                                                          #  see the note below if `git difftool` doesn't recognize it)

git merge GitNewBranch                                  # merge the branch into master

git log --oneline --graph --decorate                    # visualize the merge history

git branch -d GitNewBranch                              # delete the now-merged branch
git branch -a                                            # confirm it's gone
git status
```

**One-time P4Merge setup** (if `git difftool` doesn't already know about it):
```bash
git config --global diff.tool p4merge
git config --global difftool.p4merge.path "C:/Program Files/Perforce/p4merge.exe"
git config --global difftool.prompt false
```

---

## Lab 4 — Resolving a merge conflict

Builds directly on Lab 3's repo.

```bash
git status                                              # 1. confirm master is clean before starting

git checkout -b GitWork                                  # 2. create + switch to GitWork in one step
echo "<root><item>first</item></root>" > hello.xml
git add hello.xml
git commit -m "Add hello.xml on GitWork"

echo "<root><item>first</item><item>updated on branch</item></root>" > hello.xml
git status                                                # 3. hello.xml shows as modified
git add hello.xml
git commit -m "Update hello.xml on GitWork"               # 4. commit the branch change

git checkout master                                       # 5. switch to master

echo "<root><item>different content on master</item></root>" > hello.xml   # 6. same filename, different content
git add hello.xml
git commit -m "Add hello.xml on master with different content"             # 7.

git log --oneline --graph --decorate --all                 # 8. see both diverging histories

git diff master GitWork -- hello.xml                        # 9. command-line diff, just this file
git difftool --tool=p4merge master GitWork -- hello.xml      # 10. visual diff via P4Merge

git merge GitWork                                            # 11. attempt the merge — this WILL conflict on hello.xml
                                                               # 12. Git marks the file with <<<<<<< / ======= / >>>>>>> conflict markers

git mergetool --tool=p4merge                                  # 13. opens P4Merge's 3-way merge view (yours / theirs / base) — resolve and save

git add hello.xml
git commit -m "Resolve merge conflict in hello.xml"            # 14.

git status                                                      # 15. Git usually leaves a hello.xml.orig backup file from the mergetool
echo "*.orig" >> .gitignore                                     # add it to .gitignore

git add .gitignore
git commit -m "Ignore .orig backup files from mergetool"         # 16.

git branch -a                                                     # 17. list all branches
git branch -d GitWork                                              # 18. delete the merged branch

git log --oneline --graph --decorate                                # 19. final clean history
```

---

## Lab 5 — Clean up and push back to remote

Builds on Lab 4's repo.

```bash
git status                                              # 1. confirm master is clean

git branch -a                                            # 2. list remaining branches — should just be master (+ any remote-tracking ones)

git pull origin master                                    # 3. pull latest from remote into master

git push origin master                                     # 4. push everything pending from Lab 4 (the hello.xml history + .gitignore update)

git log --oneline --graph --decorate                         # 5. compare against the remote —
git log origin/master --oneline --graph --decorate            #    (or check the repo on GitLab/GitHub directly) to confirm it matches
```

---

## Quick command cheat-sheet (all labs)

| Command | What it does |
|---|---|
| `git init` | Turn the current folder into a Git repo |
| `git status` | Show what's staged / modified / untracked |
| `git add <file>` | Stage a file |
| `git commit -m "msg"` | Commit staged changes |
| `git branch <name>` | Create a branch |
| `git checkout <name>` / `git switch <name>` | Switch branches |
| `git checkout -b <name>` | Create + switch in one step |
| `git merge <branch>` | Merge a branch into the current one |
| `git diff <a> <b>` | Text diff between branches/commits |
| `git difftool` / `git mergetool` | Visual diff/merge (P4Merge here) |
| `git log --oneline --graph --decorate` | Compact visual commit history |
| `git branch -d <name>` | Delete a (merged) branch |
| `git pull origin master` | Fetch + merge remote changes |
| `git push origin master` | Push local commits to remote |
