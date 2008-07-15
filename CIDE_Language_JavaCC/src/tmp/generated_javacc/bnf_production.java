package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class bnf_production extends GenASTNode {
  public bnf_production(AccessModifier accessModifier, ResultType resultType, ASTStringNode identifier, FormalParameters formalParameters, ThrowsClause throwsClause, Block block, expansion_choices expansion_choices, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<AccessModifier>("accessModifier", accessModifier),
      new PropertyOne<ResultType>("resultType", resultType),
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyOne<FormalParameters>("formalParameters", formalParameters),
      new PropertyZeroOrOne<ThrowsClause>("throwsClause", throwsClause),
      new PropertyOne<Block>("block", block),
      new PropertyOne<expansion_choices>("expansion_choices", expansion_choices)
    }, firstToken, lastToken);
  }
  public bnf_production(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new bnf_production(cloneProperties(),firstToken,lastToken);
  }
  public AccessModifier getAccessModifier() {
    return ((PropertyZeroOrOne<AccessModifier>)getProperty("accessModifier")).getValue();
  }
  public ResultType getResultType() {
    return ((PropertyOne<ResultType>)getProperty("resultType")).getValue();
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public FormalParameters getFormalParameters() {
    return ((PropertyOne<FormalParameters>)getProperty("formalParameters")).getValue();
  }
  public ThrowsClause getThrowsClause() {
    return ((PropertyZeroOrOne<ThrowsClause>)getProperty("throwsClause")).getValue();
  }
  public Block getBlock() {
    return ((PropertyOne<Block>)getProperty("block")).getValue();
  }
  public expansion_choices getExpansion_choices() {
    return ((PropertyOne<expansion_choices>)getProperty("expansion_choices")).getValue();
  }
}
