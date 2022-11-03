

### 도커로 카프카 실행
- 파일만 찾을 수 있으면 명령은 어디에서든 상관 없음.
- 아래는 -f 옵션으로 파일 경로를 명시하였음. 
- -p 옵션은 프로젝트 이름. (기본은 디렉토리 이름)

```code
docker-compose -f dockerfiles/docker-compose.yml -p kafka up
```

