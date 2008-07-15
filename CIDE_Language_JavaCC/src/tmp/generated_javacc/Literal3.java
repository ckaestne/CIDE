package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Literal3 extends Literal {
  public Literal3(ASTStringNode character_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("character_literal", character_literal)
    }, firstToken, lastToken);
  }
  public Literal3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Literal3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getCharacter_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("character_literal")).getValue();
  }
}
