## Bufstream steps

- buf.yaml, buf.gen.yaml, bufstream.yaml to root
- setup proto folder including build.gradle
- add akhq.yaml to root for GUI start with: docker run -p 8080:8080 -v ./akhq.yaml:/app/application.yml tchiotludo/akhq:0.26.0)
- add buf binary to root (./bufstream serve)
- produce / consume messages. See quick'n'dirty [repository here](https://github.com/dangenendt/bufstream-test)
-  checkout[bufstream documentation](https://buf.build/docs/bufstream/)

#### For automatic payload validation on incoming messages via BSR (Buf Schema Registry), the enterprise subscription of <a className={"hover:underline text-blue-400"} href={"https://buf.build/"}>buf.build</a> is needed.  This Feature was not tested yet as i would need to spend 3000$+/m ;)