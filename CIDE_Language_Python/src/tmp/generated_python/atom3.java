package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class atom3 extends atom {
  public atom3(ASTStringNode lbracket, listmaker listmaker, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("lbracket", lbracket),
      new PropertyZeroOrOne<listmaker>("listmaker", listmaker)
    }, firstToken, lastToken);
  }
  public atom3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new atom3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getLbracket() {
    return ((PropertyOne<ASTStringNode>)getProperty("lbracket")).getValue();
  }
  public listmaker getListmaker() {
    return ((PropertyZeroOrOne<listmaker>)getProperty("listmaker")).getValue();
  }
}
