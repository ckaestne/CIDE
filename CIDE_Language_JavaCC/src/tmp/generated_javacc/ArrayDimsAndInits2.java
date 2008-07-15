package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ArrayDimsAndInits2 extends ArrayDimsAndInits {
  public ArrayDimsAndInits2(ArrayList<ASTTextNode> text557, ArrayInitializer arrayInitializer, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<ASTTextNode>("text557", text557),
      new PropertyOne<ArrayInitializer>("arrayInitializer", arrayInitializer)
    }, firstToken, lastToken);
  }
  public ArrayDimsAndInits2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ArrayDimsAndInits2(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<ASTTextNode> getText557() {
    return ((PropertyOneOrMore<ASTTextNode>)getProperty("text557")).getValue();
  }
  public ArrayInitializer getArrayInitializer() {
    return ((PropertyOne<ArrayInitializer>)getProperty("arrayInitializer")).getValue();
  }
}
