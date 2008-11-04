package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

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
