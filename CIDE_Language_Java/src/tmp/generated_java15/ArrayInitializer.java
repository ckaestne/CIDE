package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ArrayInitializer extends GenASTNode {
  public ArrayInitializer(ArrayInitializerInternal arrayInitializerInternal, ASTTextNode text19, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ArrayInitializerInternal>("arrayInitializerInternal", arrayInitializerInternal),
      new PropertyZeroOrOne<ASTTextNode>("text19", text19)
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
  public ASTTextNode getText19() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text19")).getValue();
  }
}
