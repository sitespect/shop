# shop
Sample Android App used to demo the SiteSpect Mobile SDK

<img src="https://github.com/sitespect/shop/raw/master/ShopApp.png" >

####Code Editor Example
This sample app includes a code change that works with a SiteSpect campaign. The following application code makes multilple changes to different screens in the Android App when a user is assigned to a campaign with the SDK Identifier "789" (SDK Identifier refers to the ID of a Variation Group):

- Changes image and background color of the Kitchen Category ([BrowseActivity.java](https://github.com/sitespect/shop/blob/master/AndroidShopApp/app/src/main/java/com/sitespect/sitespectdemo/browse/BrowseActivity.java#L43))
- Changes color of price text of the Featured Item ([BrowseActivity.java](https://github.com/sitespect/shop/blob/master/AndroidShopApp/app/src/main/java/com/sitespect/sitespectdemo/browse/BrowseActivity.java#L43))
- Changes icons in the Shopping Cart ([ShoppingCartActivity.java](https://github.com/sitespect/shop/blob/master/AndroidShopApp/app/src/main/java/com/sitespect/sitespectdemo/cart/ShoppingCartActivity.java#L60))
- Changes button text in Checkout ([CheckoutActivity.java](https://github.com/sitespect/shop/blob/master/AndroidShopApp/app/src/main/java/com/sitespect/sitespectdemo/checkout/CheckoutActivity.java#L40))
- Changes "Add to Cart" color ([ProductDetailsActivity.java](https://github.com/sitespect/shop/blob/master/AndroidShopApp/app/src/main/java/com/sitespect/sitespectdemo/details/ProductDetailsActivity.java#L66))

The links above will take you to the changes in the application code of each activity file. They are all wrapped around the **siteSpectCodeEditorChange function** which is defined in the [BaseSiteSpectActivity.java](https://github.com/sitespect/shop/blob/master/AndroidShopApp/app/src/main/java/com/sitespect/sitespectdemo/BaseSiteSpectActivity.java#L102) and invoked by the SiteSpect SDK for code editor changes.

Here is an example from CheckoutActivity.java:
```
    @Override
    public void siteSpectCodeEditorChange() {
        viewHolder.bottomBarActionButton.setText(R.string.checkout_buyNowButton);
    }
```    

To make all this work, we need to add **SiteSpectVersionListener** to any appropriate Activity in the Activity's onCreate (**before super.onCreate**). Since we are making change to multiple screens, we have defined SiteSpectVersionListener and added it to onCreate inside the main [DemoApplication.java](https://github.com/sitespect/shop/blob/master/AndroidShopApp/app/src/main/java/com/sitespect/sitespectdemo/DemoApplication.java#L59) file (so that it can be automatically available to other Activities).

####Live Variables
Mobile Live Variables allows managing specific variables inside SiteSpect that are used within the app. These dynamic variables are retrieved in the app using the **getLiveVariableString** call and the values are defined (in JSON format) inside SiteSpect campaigns. They can be combined with code editor changes and you can see an example of that in ([BrowseActivity.java](https://github.com/sitespect/shop/blob/master/AndroidShopApp/app/src/main/java/com/sitespect/sitespectdemo/browse/BrowseActivity.java#L48)) where the kitchen category name is a live variable:

```
        String titleValue = SiteSpect.getLiveVariableString("kitchen_text");
        if ( titleValue != null ) {
            viewHolder.kitchenCellView.setCellText(titleValue);
        }
```

Passing `{"kitchen_text":"Kitchen & Dining"}` within a SiteSpect campaign changes that text within the application for any user that is assigned to that campaign.
