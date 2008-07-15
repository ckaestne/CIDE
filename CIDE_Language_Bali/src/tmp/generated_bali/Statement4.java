package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement4 extends Statement {
  public Statement4(RegexTokenDefinition regexTokenDefinition, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<RegexTokenDefinition>("regexTokenDefinition", regexTokenDefinition)
    }, firstToken, lastToken);
  }
  public Statement4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement4(cloneProperties(),firstToken,lastToken);
  }
  public RegexTokenDefinition getRegexTokenDefinition() {
    return ((PropertyOne<RegexTokenDefinition>)getProperty("regexTokenDefinition")).getValue();
  }
}
