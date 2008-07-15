package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Label extends GenASTNode {
  public Label(ASTTextNode text17, ASTStringNode bali_token, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text17", text17),
      new PropertyOne<ASTStringNode>("bali_token", bali_token)
    }, firstToken, lastToken);
  }
  public Label(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Label(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText17() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text17")).getValue();
  }
  public ASTStringNode getBali_token() {
    return ((PropertyOne<ASTStringNode>)getProperty("bali_token")).getValue();
  }
}
