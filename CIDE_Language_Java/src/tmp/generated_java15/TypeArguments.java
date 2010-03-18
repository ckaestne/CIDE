package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeArguments extends GenASTNode {
  public TypeArguments(TypeArgument typeArgument, ArrayList<TypeArgument> typeArgument1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<TypeArgument>("typeArgument", typeArgument),
      new PropertyZeroOrMore<TypeArgument>("typeArgument1", typeArgument1)
    }, firstToken, lastToken);
  }
  public TypeArguments(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeArguments(cloneProperties(),firstToken,lastToken);
  }
  public TypeArgument getTypeArgument() {
    return ((PropertyOne<TypeArgument>)getProperty("typeArgument")).getValue();
  }
  public ArrayList<TypeArgument> getTypeArgument1() {
    return ((PropertyZeroOrMore<TypeArgument>)getProperty("typeArgument1")).getValue();
  }
}
