package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TokenKindNode extends REKind {
  public TokenKindNode(ASTStringNode _token, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("_token", _token)
    }, firstToken, lastToken);
  }
  public TokenKindNode(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TokenKindNode(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode get_token() {
    return ((PropertyOne<ASTStringNode>)getProperty("_token")).getValue();
  }
}
