package tmp.generated_html;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element9 extends Element {
  public Element9(ASTStringNode eol, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("eol", eol)
    }, firstToken, lastToken);
  }
  public Element9(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Element9(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getEol() {
    return ((PropertyOne<ASTStringNode>)getProperty("eol")).getValue();
  }
}
