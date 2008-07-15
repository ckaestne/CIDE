package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class literal4 extends literal {
  public literal4(ASTStringNode character_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("character_literal", character_literal)
    }, firstToken, lastToken);
  }
  public literal4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new literal4(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getCharacter_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("character_literal")).getValue();
  }
}
