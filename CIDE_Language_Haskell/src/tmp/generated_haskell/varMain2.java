package tmp.generated_haskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class varMain2 extends varMain {
  public varMain2(varsym varsym, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<varsym>("varsym", varsym)
    }, firstToken, lastToken);
  }
  public varMain2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new varMain2(cloneProperties(),firstToken,lastToken);
  }
  public varsym getVarsym() {
    return ((PropertyOne<varsym>)getProperty("varsym")).getValue();
  }
}
