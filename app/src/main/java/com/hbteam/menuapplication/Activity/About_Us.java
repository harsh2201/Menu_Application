package com.hbteam.menuapplication.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hbteam.menuapplication.R;
import com.shashank.sony.fancyaboutpagelib.FancyAboutPage;

public class About_Us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__us);
        FancyAboutPage fancyAboutPage=findViewById(R.id.fancyaboutpage);
        //fancyAboutPage.setCoverTintColor(Color.BLUE);  //Optional
        fancyAboutPage.setCover(R.drawable.company); //Pass your cover image
        fancyAboutPage.setName("FIDULIS BIO INCORPORATION");
        fancyAboutPage.setDescription(" Fidulis is a Latin word it means Fiducia ( Trust ) and Qualis ( Quality ) is part of the 27 years young Montage Laboratories. Since its inception in 1991, the group has been involved in developing a growing portfolio of best-in-class parental manufacturing in the health care arena. FIDULIS BIO INCORPORATION, one of the youngest group members will specifically focus on the “CRITICAL CARE” division of the group.");
        fancyAboutPage.setAppIcon(R.drawable.splash); //Pass your app icon image
        fancyAboutPage.setAppName("MANUFACTURING PLANT / RESEARCH & DEVELOPMENT");
        fancyAboutPage.setVersionNameAsAppSubTitle("1.2.3");
        fancyAboutPage.setAppDescription("FIDULIS BIO INCORPORATION’s state of art WHO-cGMP, ISO 9001:2008 approved plant is located in Dhandha, Himmatnagar, Gujarat.The plant is spread over an area of 4 acres with a built up area of well over 230,000 sq.ft.. It has separate blocks for Betalactams / Non-Betalactams / Cephalosporins / Hormones for manufacturing of Dry Powder Injectables, Lyophilized Injectables, Liquid Injectables in Vials and Ampoules, PFS. The equipments, utilities, plant and machinery meet international standards. It has a well equipped Analytical and Microbial testing facilities. The manufacturing conforms to the highest possible quality standards meeting the specifications of US, European, Indian and Other Pharmacopoeias.");
        fancyAboutPage.addEmailLink("brijpatel1823@gmail.com");     //Add your email id
        fancyAboutPage.addFacebookLink("https://www.facebook.com/brij1823");  //Add your facebook address url
        fancyAboutPage.addTwitterLink("https://twitter.com/brij1823");
        fancyAboutPage.addLinkedinLink("https://www.linkedin.com/in/brij-patel-a95733159/");
        fancyAboutPage.addGitHubLink("https://github.com/brij1823");

    }
}
