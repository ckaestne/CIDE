package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ConstructorDeclaration extends GenASTNode {
  public ConstructorDeclaration(TypeParameters typeParameters, ASTStringNode identifier, FormalParameters formalParameters, NameList nameList, ExplicitConstructorInvocation explicitConstructorInvocation, ArrayList<BlockStatement> blockStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<TypeParameters>("typeParameters", typeParameters),
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyOne<FormalParameters>("formalParameters", formalParameters),
      new PropertyZeroOrOne<NameList>("nameList", nameList),
      new PropertyZeroOrOne<ExplicitConstructorInvocation>("explicitConstructorInvocation", explicitConstructorInvocation),
      new PropertyZeroOrMore<BlockStatement>("blockStatement", blockStatement)
    }, firstToken, lastToken);
  }
  public ConstructorDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ConstructorDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public TypeParameters getTypeParameters() {
    return ((PropertyZeroOrOne<TypeParameters>)getProperty("typeParameters")).getValue();
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public FormalParameters getFormalParameters() {
    return ((PropertyOne<FormalParameters>)getProperty("formalParameters")).getValue();
  }
  public NameList getNameList() {
    return ((PropertyZeroOrOne<NameList>)getProperty("nameList")).getValue();
  }
  public ExplicitConstructorInvocation getExplicitConstructorInvocation() {
    return ((PropertyZeroOrOne<ExplicitConstructorInvocation>)getProperty("explicitConstructorInvocation")).getValue();
  }
  public ArrayList<BlockStatement> getBlockStatement() {
    return ((PropertyZeroOrMore<BlockStatement>)getProperty("blockStatement")).getValue();
  }
}
