### For developer and devlops
    > Java Version 1.8
    > Build by Maven
    > git repository: https://github.com/HuberyTest/WeatherForecast

    > build commaind: mvn package
    > build file: {workspace}/target/forecast-{version}.jar
    > health check: {domain}/api/health
    > specify log file path (e.g.): -Dlogging.file.name=/var/log/forecast/forecast.log

    > external dependency: access restful api provided by https://openweathermap.org/ for weather forecast data.

### for developer:
    > How to add a city for weather forecast? Add a entry in resources/cities/cities.json。Give it a unique id and name
    > How to add a forecast data source implementation? 
        1.  Add a new source name to ForecastQuerySourceEnum. 
        2.  Implement ForecastQuery interface. add @ConditionalOnProperty annotation, specify the property name is forecast.source.
        3.  Usually the weather forecast is queried by the city id provided by the Api provider. 
            So the id mappings should be stored in a properties file.
            The file should be put in resources/cities and the file name can be specified in ForecastQuerySourceEnum.
        4.  Configure the parameter forecast.source to use component name of new implementation in application.properties.
