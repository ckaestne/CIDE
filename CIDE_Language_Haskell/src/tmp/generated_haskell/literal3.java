package tmp.generated_haskell;

import cide.gast.ASTStringNode;
import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class literal3 extends literal {
  public literal3(ASTStringNode character_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("character_literal", character_literal)
    }, firstToken, lastToken);
  }
  public literal3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new literal3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getCharacter_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("character_literal")).getValue();
  }
}
