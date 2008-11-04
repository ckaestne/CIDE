package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ArrayDimsAndInits2 extends ArrayDimsAndInits {
  public ArrayDimsAndInits2(ArrayList<ASTTextNode> text558, ArrayInitializer arrayInitializer, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<ASTTextNode>("text558", text558),
      new PropertyOne<ArrayInitializer>("arrayInitializer", arrayInitializer)
    }, firstToken, lastToken);
  }
  public ArrayDimsAndInits2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ArrayDimsAndInits2(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<ASTTextNode> getText558() {
    return ((PropertyOneOrMore<ASTTextNode>)getProperty("text558")).getValue();
  }
  public ArrayInitializer getArrayInitializer() {
    return ((PropertyOne<ArrayInitializer>)getProperty("arrayInitializer")).getValue();
  }
}
