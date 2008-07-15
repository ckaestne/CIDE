package tmp.generated_html;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DeclTag extends GenASTNode {
  public DeclTag(ASTStringNode decl_any, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("decl_any", decl_any)
    }, firstToken, lastToken);
  }
  public DeclTag(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new DeclTag(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getDecl_any() {
    return ((PropertyOne<ASTStringNode>)getProperty("decl_any")).getValue();
  }
}
