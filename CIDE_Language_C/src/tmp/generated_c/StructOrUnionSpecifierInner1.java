package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StructOrUnionSpecifierInner1 extends StructOrUnionSpecifierInner {
  public StructOrUnionSpecifierInner1(ASTStringNode identifier, StructDeclarationList structDeclarationList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTStringNode>("identifier", identifier),
      new PropertyOne<StructDeclarationList>("structDeclarationList", structDeclarationList)
    }, firstToken, lastToken);
  }
  public StructOrUnionSpecifierInner1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StructOrUnionSpecifierInner1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public StructDeclarationList getStructDeclarationList() {
    return ((PropertyOne<StructDeclarationList>)getProperty("structDeclarationList")).getValue();
  }
}
