**CurrencyExchange**
Project allows to check currency rate:
- in real time - data is refreshed every minute
- in the past - depending on the amount of available data following range can be selected: one week, one year, two years, five years, ten years. If we don't have enough data for a given time interval, the corresponding message is returned.

List of available currencies is loaded and sorted alphabetically on application startup.
For historical data trend lines are available.


**Further development**
- load historical currencies data only ones a day (cache data in database)
- add support for smaller historical currency data ranges (12H, 24H).
- add auto-refreshing of historical currency data chart depending on the range (every 5min, 15min, etc.)
- improve calculation of trend lines
