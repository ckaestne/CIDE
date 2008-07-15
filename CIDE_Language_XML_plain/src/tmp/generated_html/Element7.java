package tmp.generated_html;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element7 extends Element {
  public Element7(ASTStringNode pcdata_qs, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("pcdata_qs", pcdata_qs)
    }, firstToken, lastToken);
  }
  public Element7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Element7(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getPcdata_qs() {
    return ((PropertyOne<ASTStringNode>)getProperty("pcdata_qs")).getValue();
  }
}
