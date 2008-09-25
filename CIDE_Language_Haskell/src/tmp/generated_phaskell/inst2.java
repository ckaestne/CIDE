package tmp.generated_phaskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyZeroOrMore;
import cide.gparser.Token;

public class inst2 extends inst {
  public inst2(gtycon gtycon1, ArrayList<tyvar> tyvar, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<gtycon>("gtycon1", gtycon1),
      new PropertyZeroOrMore<tyvar>("tyvar", tyvar)
    }, firstToken, lastToken);
  }
  public inst2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new inst2(cloneProperties(),firstToken,lastToken);
  }
  public gtycon getGtycon1() {
    return ((PropertyOne<gtycon>)getProperty("gtycon1")).getValue();
  }
  public ArrayList<tyvar> getTyvar() {
    return ((PropertyZeroOrMore<tyvar>)getProperty("tyvar")).getValue();
  }
}
