package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class typArr extends GenASTNode {
  public typArr(ASTTextNode text7, type type, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text7", text7),
      new PropertyOne<type>("type", type)
    }, firstToken, lastToken);
  }
  public typArr(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new typArr(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText7() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text7")).getValue();
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
}
