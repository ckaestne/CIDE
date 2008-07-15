package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class BaliTokenDefinition extends GenASTNode {
  public BaliTokenDefinition(ASTStringNode string, ASTStringNode bali_token, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("string", string),
      new PropertyOne<ASTStringNode>("bali_token", bali_token)
    }, firstToken, lastToken);
  }
  public BaliTokenDefinition(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new BaliTokenDefinition(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getString() {
    return ((PropertyOne<ASTStringNode>)getProperty("string")).getValue();
  }
  public ASTStringNode getBali_token() {
    return ((PropertyOne<ASTStringNode>)getProperty("bali_token")).getValue();
  }
}
