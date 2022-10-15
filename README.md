# boot-basic

## docker部署ELK（Elasticsearch、Logstash、Kibana）

### Elasticsearch

- **拉取镜像：**

  ```
  docker pull elasticsearch:7.6.2	
  ```

- **设置网络（默认是桥接模式）：**

  ```
  docker network create elasticsearch
  ```

- **查看网络：**

  ```
  docker network ls
  ```

- **启动容器（单机模式）：**

  ```
  docker run --name elasticsearch7.6.2 --net elasticsearch -d -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:7.6.2 
  ```

- **查看日志：**

  ```
  docker logs -f elasticsearch7.6.2 
  ```

- **浏览器访问：**

  ```
  http://localhost:9200
  ```

- **查询服务地址（后续需要用到）**

  ```
  docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' elasticsearch7.6.2 // 172.18.0.2
  ```

### Kibana

- **拉取镜像：**

  ```
  docker pull kibana:7.6.2	
  ```

- **启动容器：**

  ```
  docker run --name kibana7.6.2 -d -p 5601:5601 kibana:7.6.2 
  ```

- **进入容器：**

  ```
  docker exec -it kibana7.6.2 /bin/bash
  cd config
  vi kibana.yml
  // 修改为
  #
  # ** THIS IS AN AUTO-GENERATED FILE **
  #
  
  # Default Kibana configuration for docker target
  server.name: kibana
  server.host: "0"
  # elasticsearch.hosts: [ "http://elasticsearch:9200" ]
  elasticsearch.hosts: [ "http://172.18.0.2:9200" ]
  xpack.monitoring.ui.container.elasticsearch.enabled: true
  i18n.locale: "zh-CN"
  ```

- **查看日志：**

  ```
  docker logs -f kibana7.6.2 
  ```

- **浏览器访问：**

  ```
  http://localhost:5601
  ```

### Logstash

- 拉取镜像：**

  ```
  docker pull logstash:7.6.2	
  ```

- **启动容器：**

  ```
  docker run --name logstash7.6.2 -d -p 9600:9600 -p 4560:4560 logstash:7.6.2 
  ```

- **进入容器：**

  ```
  docker exec -it logstash7.6.2 /bin/bash
  
  cd config
  vi logstash.yml 
  // 修改为
  http.host: "0.0.0.0"
  xpack.monitoring.elasticsearch.hosts: [ "http://172.18.0.2:9200" ]
  
  cd..
  cd pipeline
  vi logstash.conf 
  // 修改为
  input{
      tcp {
          mode => "server"
          host => "0.0.0.0"
          port => 4560
          codec => json_lines
      }
  }
  
  output{
      elasticsearch{
          hosts=>["172.18.0.2:9200"]
          index => "boot-basic-%{+YYYY.MM.dd}"
      }
      stdout{
          codec => rubydebug
      }
  }
  ```

- **查看日志：**

  ```
  docker logs -f logstash7.6.2 
  ```

- **浏览器访问：**

  ```
  http://localhost:9600
  ```