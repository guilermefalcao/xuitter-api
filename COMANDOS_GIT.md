# Comandos Git para subir o projeto no GitHub

## Situação atual:
- Branch atual: master
- Branch de desenvolvimento: desenvolvimento_xuitter
- Repositório: https://github.com/guilermefalcao/xuitter-api

## Comandos para executar no Git Bash:

# 1. Mudar para a branch de desenvolvimento
git checkout desenvolvimento_xuitter

# 2. Adicionar todos os arquivos modificados
git add .

# 3. Fazer commit com mensagem descritiva
git commit -m "feat: Implementa feed de xuits com frontend e testes completos"

# 4. Fazer push para o GitHub
git push origin desenvolvimento_xuitter

# 5. (Opcional) Voltar para master e fazer merge
git checkout master
git merge desenvolvimento_xuitter
git push origin master

## Comandos individuais (copie e cole um por vez):

```bash
git checkout desenvolvimento_xuitter
```

```bash
git add .
```

```bash
git commit -m "feat: Implementa feed de xuits com frontend e testes completos"
```

```bash
git push origin desenvolvimento_xuitter
```

## Verificar status a qualquer momento:

```bash
git status
```

```bash
git branch
```

## Ver histórico de commits:

```bash
git log --oneline
```
