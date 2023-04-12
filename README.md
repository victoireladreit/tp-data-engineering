# Practical work 
* [Data engineering / distributed systems](https://github.com/polomarcus/tp/tree/main/data-engineering)
* [Ecodesign](https://github.com/polomarcus/tp/tree/main/ecodesign)

## Tools you need
Have a stackoverflow account : https://stackoverflow.com/

Have a github account : https://github.com/

### A Scala IDE :
1. Intellij Idea: https://www.jetbrains.com/fr-fr/idea/download
2. Scala plugin : https://www.jetbrains.com/help/idea/discover-intellij-idea-for-scala.html
3. Open Intellij and [set up the scala SDK and JDK](https://www.jetbrains.com/help/idea/sdk.html)  

**Beware** : For Spark code, we have to use **Java 17** (JDK17) or inferior, otherwise you'll have [this error](https://stackoverflow.com/a/73349341/3535853) : 
```
Extracting Structure Failed
```

### Docker and Compose
Take time to read and install

https://docs.docker.com/get-started/overview/
```
docker --version
Docker version XX.XX.XX
```

https://docs.docker.com/compose/
```
docker-compose --version # Or docker compose --version
docker-compose version X.XX.X
```

### Fork OR update the repo on your own Github account
#### Fork 
* Click on https://github.com/polomarcus/tp/fork

#### Update your fork
* https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/working-with-forks/syncing-a-fork


## Notes

# Set fork repo as upstream

```bash
 git remote -v
 
 git remote add <name> fork link
 
 git remote -v
```
All git bash commands precise <name> at the end
  
  git push <name>
