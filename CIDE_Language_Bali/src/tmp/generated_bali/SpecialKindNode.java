package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SpecialKindNode extends REKind {
  public SpecialKindNode(ASTStringNode _special_token, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("_special_token", _special_token)
    }, firstToken, lastToken);
  }
  public SpecialKindNode(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new SpecialKindNode(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode get_special_token() {
    return ((PropertyOne<ASTStringNode>)getProperty("_special_token")).getValue();
  }
}
