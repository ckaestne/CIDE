package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ArrayInitializer extends GenASTNode {
  public ArrayInitializer(ArrayInitializerInternal arrayInitializerInternal, ASTTextNode text499, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ArrayInitializerInternal>("arrayInitializerInternal", arrayInitializerInternal),
      new PropertyZeroOrOne<ASTTextNode>("text499", text499)
    }, firstToken, lastToken);
  }
  public ArrayInitializer(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ArrayInitializer(cloneProperties(),firstToken,lastToken);
  }
  public ArrayInitializerInternal getArrayInitializerInternal() {
    return ((PropertyZeroOrOne<ArrayInitializerInternal>)getProperty("arrayInitializerInternal")).getValue();
  }
  public ASTTextNode getText499() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text499")).getValue();
  }
}
