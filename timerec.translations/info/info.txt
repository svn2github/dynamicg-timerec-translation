----------------------------------------
basics
----------------------------------------

the texts are stored in the "strings.xml" file, which has this format:
<string name="techincalKey">text to translate</string>

- the file is located in the folder res/values-LANGUAGECODE, e.g. "values-fr"
- the "values" folder has the english original
- if you are more familiar with german, use the "values-de" strings file as 
template (the english and german versions are maintained by myself)



----------------------------------------
special characters
----------------------------------------

- \n is a newline. "a\nb" will show as "a<newline>b"
- single and double quotes have to be escaped with backslash - \' or \"
- {1} is a placeholder. "Edit \'{1}\'" may become "Edit 'Tasks'"

- \u2026 and similar are utf characters:
\u2026 = ellipsis [...]
\u2192 = right arrow [=>]
\u2022 = bullet

- other special characters
&amp; = ampersand [&] 
&gt; = greater than [>]
&lt; = less than [<]




-------------------------
TECHNICAL INFO
-------------------------

* the eclipse project with the resources is hosted here:
http://dynamicg-timerec.googlecode.com/svn/trunk/timerec.translations
-> if you have a google email account i can add you as 'Committer' to that project

* for direct access, the "strings" files are here
spanish: http://dynamicg-timerec.googlecode.com/svn/trunk/timerec.translations/res/values-es/strings.xml
italian: http://dynamicg-timerec.googlecode.com/svn/trunk/timerec.translations/res/values-it/strings.xml
french: http://dynamicg-timerec.googlecode.com/svn/trunk/timerec.translations/res/values-fr/strings.xml

* english and german files of the current production version
EN: http://dynamicg-timerec.googlecode.com/svn/trunk/timerec.translations/res/values/strings.xml
DE: http://dynamicg-timerec.googlecode.com/svn/trunk/timerec.translations/res/values-de/strings.xml

* see the readme for more details
https://dynamicg-timerec.googlecode.com/svn/trunk/timerec.translations/README.txt

* the text for the Android Market is here (long app description and short "Promo" text):
https://dynamicg-timerec.googlecode.com/svn/trunk/timerec.translations/market-description/

