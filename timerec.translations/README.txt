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
