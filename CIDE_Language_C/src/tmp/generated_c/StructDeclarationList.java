package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StructDeclarationList extends GenASTNode {
  public StructDeclarationList(StructDeclaration structDeclaration, ArrayList<StructDeclaration> structDeclaration1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<StructDeclaration>("structDeclaration", structDeclaration),
      new PropertyZeroOrMore<StructDeclaration>("structDeclaration1", structDeclaration1)
    }, firstToken, lastToken);
  }
  public StructDeclarationList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StructDeclarationList(cloneProperties(),firstToken,lastToken);
  }
  public StructDeclaration getStructDeclaration() {
    return ((PropertyOne<StructDeclaration>)getProperty("structDeclaration")).getValue();
  }
  public ArrayList<StructDeclaration> getStructDeclaration1() {
    return ((PropertyZeroOrMore<StructDeclaration>)getProperty("structDeclaration1")).getValue();
  }
}
