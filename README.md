# LocationWeatherApp

![](https://media.giphy.com/media/VJr6cTFPBgAYGimacq/giphy.gif) 

MVP + Dagger2 + RxJava + Retrofit + EventBus + ButterKnife + Gson + Picasso + Dexter + AndroidX
-----------------------------------------------------------------------------
- Uygulama ilk açıldığında konum ve seçili şehir olmadığı için default olarak İzmir hava durumu gösteriliyor.
- Eğer seçili şehir varsa ancak konum açık ise kullanıcının bulunduğu konumun hava durumu gösteriliyor.
- Şehirler listesinde en az 1 şehir seçili olacak şekilde düzenlendi.
-----------------------------------------------------------------------------
**Paketler, Sınıflar ve Açıklamalar:** (Alt bileşenler maddeler halinde açıklanmıştır)
-------------
**Data:** Projedeki Servis ve SharedPreferences gibi işlemlerinin bulunduğu klasör.
- **Model:** Servis ile gelen bilgileri almak için model classlarımızın bulunduğu klasör.
- **Service:** Sunucudan gelen response'u UI'ya aktarmak için kullandığım "ServiceCallBack" interface'ini ve servis implementasyonlarını bulunduran klasör.
- **Pref:** Uygulamada GPS takibi, seçili şehirlerin takibi gibi işlemlerde kullandığımız SharedPreferences işlemlerini kolay bir şekilde yönetebilmemizi sağlayan, metodları sağlayan "PrefHelper" interface'ni ve PrefHelperde bulunan metodları implement ettiğimiz "AppPrefHelper" classını bulunduran klasör.
- **DataManager.java:** ApiHelper ve PrefHelper interface'lerini imlement eden ve tüm metodlarını kullanabilmemizi sağlayan interface.
- **AppDataManager.java:** Servis ve SharedPreferences olaylarını tek bir sınıftan kontrol edebilmemizi sağlayan class. Bu sınıf sayesinde
ApiHelper ve PrefHelper metodlarına ulaşabiliyoruz. Kullanılmak istenen sınıflarda @Inject edilerek sınıf nesnesine erişebiliyoruz.

**Di:** Dagger2 modul ve componentlerinin bulunduğu klasör. DataManager nesnesini Dagger2 ile oluşturuyoruz. Bu sayede servis ve preferences işlemlerini kolay bir şekilde yöneteibliyoruz.
- **DaggerComponent.java:** Dagger modülünü belirttiğim ve Dagger2'yi kullanacağım UI bileşenlerini belirlediğim sınıf.
- **DaggerModule.java:** DataManager nesnesini kullanabilmemiz için servisleri, arayüzleri oluşturan sınıf.

**EventBus:** Eventbus kütüphanesinde kullanacağım sınıfları bulundurduğum klasör.

**Ui:** Activity, Adapter gibi bileşenlerin bulunduğu MVP mantığının işletildiği klasör.

**Utility:** Kullanıcıdan konum isteme, tarih tipi dönüşümü gibi işlemler için kullandığım sınıfları ve metodları bulunduran klasör.


 



