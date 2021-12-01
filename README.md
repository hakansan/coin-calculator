# Coin Calculator

## Gereksinim

Java 11, docker, docker-compose

## Derleme

Projeyi derlemek için;

``` shell 
mvn install
```

Bu işlemden sonra `hakansandikkaya/coin-calculator:1.0` docker imajı oluşacaktır.

Proje dizinindeki docker-compose dosyası çalıştırılmalıdır.

``` shell 
docker-compose up -d
```

Bu işlem gerçekleştirildikten sonra `http://localhost:8081/swagger-ui/` adresinden swagger arayüzünü görüyor
olmalısınız.

## Kullanım

Öncelikle **docker-compose** dosyasının ayağa kalktığından emin olun. 3 adet endpoint görüyor olmalısınız.
Bunlardan `api/calculate` girilen miktara göre istenilen crypto değerinin hesaplamasını yapmaktadır. Bu işlemi
yaparken **Blockchain** servisinden istenilen fiyat bilgisini almaktadır. Alınan fiyat bilgisi hafızada 10 saniyeliğine
tutlmaktadır. Kullanıcı bu süre içinde işlemi tamamlayıp kaydet dediğinde (`api/save`) sistem bu süre içerisinde olup
olmadığını kontrol ederek veritabanına kayıt işlemini yapacaktır. Süre geçtiğinde bir uyarı mesajı ile yanıt
dönülecektir ve yeniden fiyat bilgisi sorgulanarak kullanıma sunulacaktır.
`api/currencies` endpointi ise listelenecek olan fiat ve crypto listesini dönmektedir.

Sonuç olarak her kullanıcı için 10 saniye boyunca fiyat bilgisi hafızada tutularak kayıt işlemi sırasında bu cacheId ile
süresi geçmemiş olan fiyata göre hesaplama yapılıp kayıt edilmektedir.