## About the app
Uses the Investors Exchange(IEX) api to get a list of stock data in json format. The list of stocks is displyed 
in the first screen. Clicking on a listing takes the user to the details screen where the stock pricing information, 
company information and ohcl chart of the stock price is displayed.

## Working of the app
1. The app fetches three listings (most active stocks, gainers and losers) from the api and 
cosolidates them into a single list in the ui.
2. Clicking on the stock listing fetched more details like quote, company information and chart from the api
and displays it on the Details screen

### Libraries used
1. Retrofit2
2. OkHTTP3
3. Picasso
4. RxJava2
5. Dagger2
6. Room
7. ConstraintLayout
8. Android Support Libraries
