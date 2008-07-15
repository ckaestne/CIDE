package tmp.generated_html;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element5 extends Element {
  public Element5(ASTStringNode tag_start, ASTStringNode lst_error, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("tag_start", tag_start),
      new PropertyOne<ASTStringNode>("lst_error", lst_error)
    }, firstToken, lastToken);
  }
  public Element5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Element5(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getTag_start() {
    return ((PropertyOne<ASTStringNode>)getProperty("tag_start")).getValue();
  }
  public ASTStringNode getLst_error() {
    return ((PropertyOne<ASTStringNode>)getProperty("lst_error")).getValue();
  }
}
