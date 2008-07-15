package tmp.generated_html;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element8 extends Element {
  public Element8(ASTStringNode pcdata_q, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("pcdata_q", pcdata_q)
    }, firstToken, lastToken);
  }
  public Element8(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Element8(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getPcdata_q() {
    return ((PropertyOne<ASTStringNode>)getProperty("pcdata_q")).getValue();
  }
}
